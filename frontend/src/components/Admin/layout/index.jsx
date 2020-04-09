import React, { Component } from 'react';

import Header from './header';
import Leftnav from './leftnav';
import Footer from './footer';
import { verticalMenu } from './menu';

class Layout extends Component {
  constructor(props) {
    super(props);
    this.state = {
      user: null,
      leftNavOpen: true,
      userDropdown: false
    };
    this.handleToggle = this.handleToggle.bind(this);
    this.handleUserToggle = this.handleUserToggle.bind(this);
  }

  handleToggle() {
    this.setState(prevState => ({ ...prevState, leftNavOpen: !this.state.leftNavOpen }));
  }

  handleUserToggle() {
    this.setState(prevState => ({ ...prevState, userDropdown: !this.state.userDropdown }));
  }

  render() {
    const { children, history, pageTitle } = this.props;
    const { leftNavOpen, userDropdown } = this.state;

    return (
      <div className={`nav-fixed${!leftNavOpen ? ' sidenav-toggled' : ''}`}>
        <Header
          userDropdown={userDropdown}
          onToggle={this.handleToggle}
          userDropdownToggle={this.handleUserToggle}
        />
        <div id="layoutSidenav">
          <Leftnav history={history} menu={verticalMenu} />
          <div id="layoutSidenav_content">
            <main>
              <div className="page-header pb-10 page-header-dark bg-gradient-primary-to-secondary">
                <div className="container-fluid">
                  <div className="page-header-content">
                    <h1 className="page-header-title">
                      <span>{pageTitle}</span>
                    </h1>
                  </div>
                </div>
              </div>
              <div className="container-fluid mt-n10">{children}</div>
            </main>
            <Footer />
          </div>
        </div>
      </div>
    );
  }
}

export default Layout;
