import React from 'react';
import { Link } from 'react-router-dom';
import FeatherIcon from 'feather-icons-react';

const Header = ({ onToggle, userDropdown, userDropdownToggle }) => {
  return (
    <nav className="topnav navbar navbar-expand shadow navbar-light bg-white" id="sidenavAccordion">
      <Link className="navbar-brand d-none d-sm-block active" to="/admin">
        E-Commerce
      </Link>
      <button
        className="btn btn-icon btn-transparent-dark order-1 order-lg-0 mr-lg-2"
        id="sidebarToggle"
        onClick={onToggle}
      >
        <FeatherIcon icon="menu" />
      </button>
      <ul className="navbar-nav align-items-center ml-auto">
        <li
          className={`nav-item dropdown no-caret mr-3 dropdown-user${userDropdown ? ' show' : ''}`}
        >
          <a
            className="btn btn-icon btn-transparent-dark dropdown-toggle"
            id="navbarDropdownUserImage"
            href="#!"
            onClick={userDropdownToggle}
          >
            <img className="img-fluid" src="https://source.unsplash.com/QAB-WJcbgJk/60x60" alt="" />
          </a>
          <div
            className={`dropdown-menu dropdown-menu-right border-0 shadow animated--fade-in-up${
              userDropdown ? ' show' : ''
            }`}
          >
            <h6 className="dropdown-header d-flex align-items-center">
              <img
                className="dropdown-user-img"
                alt=""
                src="https://source.unsplash.com/QAB-WJcbgJk/60x60"
              />
              <div className="dropdown-user-details">
                <div className="dropdown-user-details-name">Valerie Luna</div>
                <div className="dropdown-user-details-email">vluna@aol.com</div>
              </div>
            </h6>
            <div className="dropdown-divider"></div>
            <a className="dropdown-item" href="#!">
              <div className="dropdown-item-icon">
                <FeatherIcon icon="settings" />
              </div>
              Account
            </a>
            <a className="dropdown-item" href="#!">
              <div className="dropdown-item-icon">
                <FeatherIcon icon="log-out" />
              </div>
              Logout
            </a>
          </div>
        </li>
      </ul>
    </nav>
  );
};

export default Header;
