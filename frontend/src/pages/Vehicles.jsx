import { useEffect, useState } from "react";

function Vehicles() {
  const [vehicles, setVehicles] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  const fetchVehicles = async () => {
    try {
      setLoading(true);
      setError("");

      const response = await fetch("/api/vehicles");

      if (!response.ok) {
        throw new Error("Failed to fetch vehicles");
      }

      const data = await response.json();

      setVehicles(data);
    } catch (err) {
      console.error("Vehicle fetch error:", err);
      setError("Unable to load vehicles");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchVehicles();

    // Refresh every 5 seconds
    const interval = setInterval(fetchVehicles, 5000);

    return () => clearInterval(interval);
  }, []);

  return (
    <div>
      <h1>Vehicles</h1>
      <p>Registered XEV vehicles</p>

      {loading && <p>Loading vehicles...</p>}

      {error && <p>{error}</p>}

      {!loading && !error && (
        <table>
          <thead>
            <tr>
              <th>Vehicle ID</th>
              <th>Model</th>
              <th>Manufacturer</th>
              <th>Manufacturing Year</th>
              <th>Status</th>
            </tr>
          </thead>

          <tbody>
            {vehicles.length > 0 ? (
              vehicles.map((vehicle) => (
                <tr key={vehicle.id}>
                  <td>{vehicle.vin}</td>
                  <td>{vehicle.model}</td>
                  <td>{vehicle.manufacturer}</td>
                  <td>{vehicle.manufacturingYear}</td>
                  <td>Registered</td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="5">
                  No vehicles found.
                </td>
              </tr>
            )}
          </tbody>
        </table>
      )}

      {!loading && !error && vehicles.length === 0 && (
        <p>No vehicle data available.</p>
      )}
    </div>
  );
}

export default Vehicles;