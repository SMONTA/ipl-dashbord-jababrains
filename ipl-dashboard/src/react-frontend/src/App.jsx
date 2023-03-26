import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { TeamPage } from './pages/TeamPage'

function App() {
  const [count, setCount] = useState(0)

  return (
    <div className="App">
      <TeamPage />
    </div>
  )
}

export default App
