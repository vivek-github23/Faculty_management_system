import React from 'react';

import '../styles/navbar.css';
import { Link } from 'react-router-dom';

/*
  This component is used for rendering the Nav Bar which contains the following,
  - Welcome message for the logged in user
  - Logout Button
  - In more complex webpages you can include routes here with the help of React-Router
*/
const NavBar = ({ user, setUser }) => {
  // If the Logout button has been clicked then clear the loggedInUser object from localStorage and
  // update "user" state to null, in order to logout, otherwise on the next reload, the Effect hook will again read the user
  // from the localStorage and relogin without showing the login form
  const logout = () => {
    window.localStorage.removeItem('loggedInUser')
    setUser(null)
  }

  // Prevents Key Exception errors if "user" state hasn't loaded yet
  if (!user)
    return null

  // Fully styled Navbar using Bootstrap (it can be a big pain to style Navbars)
  return (
    <div className="navbar">
      <div className="navbar-brand">
        <svg xmlns="http://www.w3.org/2000/svg" width="35" height="35" fill="white" class="bi bi-mortarboard" viewBox="0 0 16 16">
        <path d="M8.211 2.047a.5.5 0 0 0-.422 0l-7.5 3.5a.5.5 0 0 0 .025.917l7.5 3a.5.5 0 0 0 .372 0L14 7.14V13a1 1 0 0 0-1 1v2h3v-2a1 1 0 0 0-1-1V6.739l.686-.275a.5.5 0 0 0 .025-.917l-7.5-3.5ZM8 8.46 1.758 5.965 8 3.052l6.242 2.913z"/>
        <path d="M4.176 9.032a.5.5 0 0 0-.656.327l-.5 1.7a.5.5 0 0 0 .294.605l4.5 1.8a.5.5 0 0 0 .372 0l4.5-1.8a.5.5 0 0 0 .294-.605l-.5-1.7a.5.5 0 0 0-.656-.327L8 10.466zm-.068 1.873.22-.748 3.496 1.311a.5.5 0 0 0 .352 0l3.496-1.311.22.748L8 12.46l-3.892-1.556Z"/>
        </svg>
      <Link to="/" className="left-button">Home</Link>
      </div>
    <div className='heading'>
      <h4>International Institute of Information Technology</h4>
    </div>
      <div className="right-buttons">
        {/* <Link to="/salary">Salary</Link> */}
        <Link to="/profile">Profile</Link>
        <button onClick={logout}>Logout</button>
      </div>
    </div>
  )
}

export default NavBar