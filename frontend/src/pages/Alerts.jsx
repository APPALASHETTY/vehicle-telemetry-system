import { useEffect, useState } from "react";

function Alerts() {
  const [alerts, setAlerts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  const fetchAlerts = async () => {
    try {
      const response = await fetch("/api/alerts");

      if (!response.ok) {
        throw new Error("Failed to fetch alerts");
      }

      const data = await response.json();

      // Show newest alerts first
      setAlerts([...data].reverse());
      setError("");
    } catch (err) {
      console.error("Alert fetch error:", err);
      setError("Unable to load alerts");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchAlerts();

    // Refresh every 5 seconds
    const interval = setInterval(fetchAlerts, 5000);

    return () => clearInterval(interval);
  }, []);

  return (
    <div>
      <h1>Active Alerts</h1>
      <p>Alerts generated from vehicle telemetry data</p>

      {loading && <p>Loading alerts...</p>}

      {error && <p>{error}</p>}

      {!loading && !error && (
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>VIN</th>
              <th>Alert Type</th>
              <th>Message</th>
              <th>Severity</th>
              <th>Timestamp</th>
            </tr>
          </thead>

          <tbody>
            {alerts.map((alert) => (
              <tr key={alert.id}>
                <td>{alert.id}</td>
                <td>{alert.vin}</td>
                <td>{alert.alertType}</td>
                <td>{alert.message}</td>
                <td>{alert.severity}</td>
                <td>{alert.timestamp}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}

      {!loading && !error && alerts.length === 0 && (
        <p>No alerts available.</p>
      )}
    </div>
  );
}

export default Alerts;