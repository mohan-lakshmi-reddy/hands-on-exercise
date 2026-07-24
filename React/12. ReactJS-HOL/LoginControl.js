import React, { Component } from "react";
function UserGreeting() {
  return (
    <div>
      <h1>Welcome Back</h1>
    </div>
  );
}
function GuestGreeting() {
  return (
    <div>
      <h1>Please Sign Up.</h1>
    </div>
  );
}
function Greeting(props) {
  if (props.isLoggedIn) {
    return <UserGreeting />;
  }
  return <GuestGreeting />;
}
function LoginButton(props) {
  return (
    <button onClick={props.onClick}>
      Login</button>
  );
}
function LogoutButton(props) {
  return (
    <button onClick={props.onClick}>
      Logout</button>
  );
}
class LoginControl extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isLoggedIn: false
    };
  }
  handleLoginClick = () => {
    this.setState({
      isLoggedIn: true
    });
  };
  handleLogoutClick = () => {
    this.setState({
      isLoggedIn: false
    });
  };
  render() {
    let button;
    if (this.state.isLoggedIn) {
      button =
        <LogoutButton onClick={this.handleLogoutClick} />;
    } else {
      button =
        <LoginButton onClick={this.handleLoginClick} />;
    }
    return (
      <div>
        <Greeting
          isLoggedIn={this.state.isLoggedIn}
      />
        {button}

      </div>
    );
  }
}
export default LoginControl;