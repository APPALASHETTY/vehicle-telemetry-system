import { useEffect, useState } from "react";

function Notifications() {
  const [notifications, setNotifications] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  const fetchNotifications = async () => {
    try {
      const response = await fetch("/api/notifications");

      if (!response.ok) {
        throw new Error("Failed to fetch notifications");
      }

      const data = await response.json();

      // Show newest notifications first
      setNotifications([...data].reverse());
      setError("");
    } catch (err) {
      console.error("Notification fetch error:", err);
      setError("Unable to load notifications");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchNotifications();

    // Refresh every 5 seconds
    const interval = setInterval(fetchNotifications, 5000);

    return () => clearInterval(interval);
  }, []);

  return (
    <div>
      <h1>Notifications</h1>

      <p>Notifications generated from vehicle alerts</p>

      {loading && <p>Loading notifications...</p>}

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
            {notifications.map((notification) => (
              <tr key={notification.id}>
                <td>{notification.id}</td>
                <td>{notification.vin}</td>
                <td>{notification.alertType}</td>
                <td>{notification.message}</td>
                <td>{notification.severity}</td>
                <td>{notification.timestamp}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}

      {!loading && !error && notifications.length === 0 && (
        <p>No notifications available.</p>
      )}
    </div>
  );
}

export default Notifications;