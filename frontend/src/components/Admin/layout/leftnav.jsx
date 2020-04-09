import React from 'react';
import { Link } from 'react-router-dom';
import FeatherIcon from 'feather-icons-react';

const Leftnav = ({ menu, history }) => {
  return (
    <div id="layoutSidenav_nav">
      <nav className="sidenav shadow-right sidenav-light">
        <div className="sidenav-menu">
          <div className="nav accordion" id="accordionSidenav">
            <div className="sidenav-menu-heading">Menu</div>
            {menu.map(({ path, name, icon }, index) => (
              <Link
                className={`nav-link${path === history.location.pathname ? ' active' : ''}`}
                to={path}
                key={index}
              >
                <div className="nav-link-icon">
                  <FeatherIcon icon={icon} />
                </div>
                {name}
              </Link>
            ))}
          </div>
        </div>
        <div className="sidenav-footer">
          <div className="sidenav-footer-content">
            <div className="sidenav-footer-subtitle">Logged in as:</div>
            <div className="sidenav-footer-title">Valerie Luna</div>
          </div>
        </div>
      </nav>
    </div>
  );
};

export default Leftnav;
