import React, { useState } from 'react'
import '../styles/login.css';

/*
  This component renders the Login Form with all its functionalities
  startLogin is the method that uses the axios service to submit login credentials to the backend
*/
const LoginForm = ({ startLogin }) => {
  // States for tracking form input which are the email address and password
  const [ email, setEmail ] = useState('')
  const [ password, setPassword ] = useState('')

  // onSubmit event handler of this form
  const handleLogin = (event) => {
    // Preventing default submission of the form to the action attribute URL
    event.preventDefault()

    const credentials = {
      email, password
    }

    // Calling startLogin with the provided credentials that will make a call to the backend
    startLogin(credentials)

    // Reset the form state, i.e. the text that's on the username and password text boxes to empty strings
    setEmail('')
    setPassword('')
  }

  // Typically keep id attributes on your HTML elements so that they can be styled using CSS
  return (
    <div className="login-container">
    <form onSubmit={handleLogin} id='login-form'>
      <input 
        type='email'
        placeholder='Email'
        // Note that the text that's displayed on the textbox (value attribute) is controlled by the email state
        value={email}
        // onChange event handler
        // When you type something on the textbox, the onChange event handler will be triggered
        // This event handler as written below, updates the email state with what's being typed by the user
        // Because a state has been updated, the form component will be re-rendered and you can see the updated input
        // on the screen, obviously this happens so fast that you cannot see it but this is the entire procedure
        onChange={event => setEmail(event.target.value)}
        id='email'
        required
      />
        
      {/* Same as the above username input, except this one has the type password */}
      <input
        type='password'
        placeholder='Password'
        value={password}
        onChange={event => setPassword(event.target.value)}
        id='password'
        required
      />

      {/* Submit button for the form */}
      <button type='submit' id='login-submit'>LOGIN</button>
    </form>
    </div>
  )
}

export default LoginForm