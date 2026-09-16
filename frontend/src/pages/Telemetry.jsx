import { useEffect, useState } from "react";

function Telemetry() {
  const [telemetry, setTelemetry] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  const fetchTelemetry = async () => {
    try {
      const response = await fetch("/api/telemetry");

      if (!response.ok) {
        throw new Error("Failed to fetch telemetry");
      }

      const data = await response.json();

      // Show newest records first
      setTelemetry([...data].reverse());
      setError("");
    } catch (err) {
      console.error("Telemetry fetch error:", err);
      setError("Unable to load telemetry data");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchTelemetry();

    // Refresh every 5 seconds
    const interval = setInterval(fetchTelemetry, 5000);

    return () => clearInterval(interval);
  }, []);

  return (
    <div>
      <h1>Vehicle Telemetry</h1>
      <p>Live telemetry data received from connected vehicles</p>

      {loading && <p>Loading telemetry...</p>}

      {error && <p>{error}</p>}

      {!loading && !error && (
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>VIN</th>
              <th>Speed (km/h)</th>
              <th>Engine Temperature (°C)</th>
              <th>Latitude</th>
              <th>Longitude</th>
              <th>Timestamp</th>
            </tr>
          </thead>

          <tbody>
            {telemetry.map((item) => (
              <tr key={item.id}>
                <td>{item.id}</td>
                <td>{item.vin}</td>
                <td>{Number(item.speed).toFixed(1)}</td>
                <td>{Number(item.engineTemperature).toFixed(1)}</td>
                <td>{Number(item.latitude).toFixed(5)}</td>
                <td>{Number(item.longitude).toFixed(5)}</td>
                <td>{item.timestamp}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}

      {!loading && !error && telemetry.length === 0 && (
        <p>No telemetry data available.</p>
      )}
    </div>
  );
}

export default Telemetry;