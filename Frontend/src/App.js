import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './Home';
import LoginForm from './components/LoginForm';
import Salary from './components/Salary';
import NavBar from './components/NavBar';
import Profile from './components/Profile';
import Footer from './components/Footer';

const App = () => {
  // Your logout and other functions can be defined here

  return (
    <Router>
      <div className="App">
        <NavBar />
        <div className="content">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<LoginForm />} />
            <Route path="/salary" element={<Salary />} />
            <Route path="/profile" element={<Profile />} />
          </Routes>
        </div>
        <Footer />
      </div>
    </Router>
  );
}

export default App;
