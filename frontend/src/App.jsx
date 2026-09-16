import { BrowserRouter, Routes, Route, Link } from 'react-router-dom'
import Dashboard from './pages/Dashboard'
import Vehicles from './pages/Vehicles'
import Telemetry from './pages/Telemetry'
import Alerts from './pages/Alerts'
import Notifications from './pages/Notifications'
import Users from './pages/Users'
import './App.css'

function App() {
  return (
    <BrowserRouter>
      <div className="app">

        <header className="header">
          <h1>🚗 Vehicle Telemetry System</h1>
        </header>

        <div className="layout">

          <nav className="sidebar">

            <Link to="/">🏠 Dashboard</Link>

            <Link to="/vehicles">🚗 Vehicles</Link>

            <Link to="/telemetry">📡 Telemetry</Link>

            <Link to="/alerts">⚠️ Alerts</Link>

            <Link to="/notifications">🔔 Notifications</Link>

            <Link to="/users">👤 Users</Link>

          </nav>

          <main className="main-content">

            <Routes>

              <Route path="/" element={<Dashboard />} />

              <Route path="/vehicles" element={<Vehicles />} />

              <Route path="/telemetry" element={<Telemetry />} />

              <Route path="/alerts" element={<Alerts />} />

              <Route
                path="/notifications"
                element={<Notifications />}
              />

              <Route path="/users" element={<Users />} />

            </Routes>

          </main>

        </div>

      </div>
    </BrowserRouter>
  )
}

export default App