import { useState } from 'react'

import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import './App.css'
import { MatchPage } from './pages/MatchPage';
import { TeamPage } from './pages/TeamPage'

function App() {
  const [count, setCount] = useState(0)

  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/teams/:teamName/matches/:year" element={<MatchPage />} />
          <Route path="/teams/:teamName" element={<TeamPage />} />
        </Routes>
      </Router>
    </div>
  )
}

export default App
