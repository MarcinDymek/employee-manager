import React, { Component } from 'react';

class NavigationBar extends Component {
    constructor(props){
        super(props)

        this.state = {

        }
    }

    render() {
        return (
            <nav className="navbar navbar-expand-md navbar-dark bg-dark">
            <ul style={{marginTop: "48%"}}>
            <li><a href="/" className="navbar-brand" style={{marginLeft: "10px"}}>Home</a></li>
            <li><a href="/" className="navbar-brand" style={{marginLeft: "10px"}}>Employees</a></li>
            <li><a href="/" className="navbar-brand" style={{marginLeft: "10px"}}>Logout</a></li>
            </ul>

        </nav>
        );
    }
}

export default NavigationBar;