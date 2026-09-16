import { useState } from "react";

function Dashboard() {
  const [selectedVehicle, setSelectedVehicle] = useState("XEV 9e");

  return (
    <div className="dashboard">

      {/* Dashboard Header */}
      <div className="dashboard-header">
        <div>
          <h1>Vehicle Telemetry Dashboard</h1>
          <p>Connected EV Monitoring Platform</p>
        </div>

        <div className="system-status">
          <span className="status-dot"></span>
          System Online
        </div>
      </div>


      {/* Fleet Overview */}
      <section>
        <h2 className="section-title">Fleet Overview</h2>

        <div className="stats-grid">

          <div className="stat-card">
            <span className="stat-label">
              Total Vehicles
            </span>

            <span className="stat-value">
              5
            </span>

            <span className="stat-description">
              Registered vehicles
            </span>
          </div>


          <div className="stat-card">
            <span className="stat-label">
              Online Vehicles
            </span>

            <span className="stat-value">
              3
            </span>

            <span className="stat-description">
              Currently connected
            </span>
          </div>


          <div className="stat-card">
            <span className="stat-label">
              Average SOC
            </span>

            <span className="stat-value">
              82%
            </span>

            <span className="stat-description">
              Fleet battery level
            </span>
          </div>


          <div className="stat-card">
            <span className="stat-label">
              Active Alerts
            </span>

            <span className="stat-value alert-value">
              2
            </span>

            <span className="stat-description">
              Require attention
            </span>
          </div>

        </div>
      </section>


      {/* Vehicle Overview */}
      <section>

        <div className="section-heading-row">

          <h2 className="section-title">
            Vehicle Overview
          </h2>

          <select
            className="vehicle-select"
            value={selectedVehicle}
            onChange={(e) => setSelectedVehicle(e.target.value)}
          >
            <option value="XEV 9e">
              XEV 9e - VIN003
            </option>

            <option value="XEV 6e">
              XEV 6e - VIN004
            </option>
          </select>

        </div>


        <div className="vehicle-overview-card">

          <div className="vehicle-info">

            <div>

              <span className="vehicle-model">
                {selectedVehicle}
              </span>

              <span className="vehicle-vin">
                {selectedVehicle === "XEV 9e"
                  ? "VIN003"
                  : "VIN004"}
              </span>

            </div>


            <div className="vehicle-online">

              <span className="status-dot"></span>

              Connected

            </div>

          </div>


          <div className="telemetry-grid">

            <div className="telemetry-item">
              <span>Battery SOC</span>
              <strong>82%</strong>
            </div>


            <div className="telemetry-item">
              <span>Estimated Range</span>
              <strong>384 km</strong>
            </div>


            <div className="telemetry-item">
              <span>Vehicle Speed</span>
              <strong>68 km/h</strong>
            </div>


            <div className="telemetry-item">
              <span>Motor Temperature</span>
              <strong>72 °C</strong>
            </div>


            <div className="telemetry-item">
              <span>Battery Temperature</span>
              <strong>31 °C</strong>
            </div>


            <div className="telemetry-item">
              <span>Power</span>
              <strong>42 kW</strong>
            </div>

          </div>

        </div>

      </section>


      {/* Vehicle Health */}
      <section>

        <h2 className="section-title">
          Vehicle Health
        </h2>


        <div className="health-grid">

          <div className="health-card">
            <span>Battery System</span>

            <strong className="health-good">
              ● Normal
            </strong>
          </div>


          <div className="health-card">
            <span>Powertrain</span>

            <strong className="health-good">
              ● Normal
            </strong>
          </div>


          <div className="health-card">
            <span>Thermal System</span>

            <strong className="health-good">
              ● Normal
            </strong>
          </div>


          <div className="health-card">
            <span>Connectivity</span>

            <strong className="health-good">
              ● Connected
            </strong>
          </div>

        </div>

      </section>


      {/* Recent Vehicle Activity */}
      <section>

        <h2 className="section-title">
          Recent Vehicle Activity
        </h2>


        <div className="vehicle-activity-grid">


          {/* XEV 9e - VIN003 */}
          <div className="activity-card">

            <div className="activity-header">

              <div>
                <h3>XEV 9e</h3>
                <span>VIN003</span>
              </div>

              <span className="status-badge active">
                Active
              </span>

            </div>


            <div className="activity-details">

              <div>
                <span>Speed</span>
                <strong>68 km/h</strong>
              </div>

              <div>
                <span>Battery SOC</span>
                <strong>82%</strong>
              </div>

              <div>
                <span>Temperature</span>
                <strong>72 °C</strong>
              </div>

            </div>

          </div>


          {/* XEV 6e - VIN004 */}
          <div className="activity-card">

            <div className="activity-header">

              <div>
                <h3>XEV 6e</h3>
                <span>VIN004</span>
              </div>

              <span className="status-badge active">
                Active
              </span>

            </div>


            <div className="activity-details">

              <div>
                <span>Speed</span>
                <strong>52 km/h</strong>
              </div>

              <div>
                <span>Battery SOC</span>
                <strong>64%</strong>
              </div>

              <div>
                <span>Temperature</span>
                <strong>69 °C</strong>
              </div>

            </div>

          </div>


          {/* XEV 9e - VIN005 */}
          <div className="activity-card">

            <div className="activity-header">

              <div>
                <h3>XEV 9e</h3>
                <span>VIN005</span>
              </div>

              <span className="status-badge inactive">
                Parked
              </span>

            </div>


            <div className="activity-details">

              <div>
                <span>Speed</span>
                <strong>0 km/h</strong>
              </div>

              <div>
                <span>Battery SOC</span>
                <strong>91%</strong>
              </div>

              <div>
                <span>Temperature</span>
                <strong>30 °C</strong>
              </div>

            </div>

          </div>


          {/* XEV 6e - VIN006 */}
          <div className="activity-card">

            <div className="activity-header">

              <div>
                <h3>XEV 6e</h3>
                <span>VIN006</span>
              </div>

              <span className="status-badge active">
                Active
              </span>

            </div>


            <div className="activity-details">

              <div>
                <span>Speed</span>
                <strong>45 km/h</strong>
              </div>

              <div>
                <span>Battery SOC</span>
                <strong>74%</strong>
              </div>

              <div>
                <span>Temperature</span>
                <strong>67 °C</strong>
              </div>

            </div>

          </div>

        </div>

      </section>


      {/* Active Alerts */}
      <section>

        <h2 className="section-title">
          Active Alerts
        </h2>


        <div className="alerts-grid">

          <div className="alert-card critical">

            <div>
              <strong>
                High Speed
              </strong>

              <p>
                VIN003 · XEV 9e
              </p>
            </div>

            <span>
              CRITICAL
            </span>

          </div>


          <div className="alert-card warning">

            <div>
              <strong>
                Battery Temperature
              </strong>

              <p>
                VIN004 · XEV 6e
              </p>
            </div>

            <span>
              WARNING
            </span>

          </div>

        </div>

      </section>

    </div>
  );
}

export default Dashboard;