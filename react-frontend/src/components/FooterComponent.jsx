import React, { Component } from 'react';

class FooterComponent extends Component {
    footerStyle = {
        position: "fixed",
        bottom: "0",
        width: "100%",
        height: "25px",
    };

    render() {
        return (
            <div>
                <footer style={this.footerStyle} className = "footer">
                    <span className="text-muted">All Rights Reserved 2021 @</span>

                </footer>
            </div>
        );
    }
}

export default FooterComponent;