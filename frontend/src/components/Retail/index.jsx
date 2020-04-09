import React from 'react';

import logo from '../../assets/logo.svg';
import './index.css';

const Homepage = () => {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/components/Retail/index.js</code> and save to reload.
        </p>
        <a className="App-link" href="/admin" rel="noopener noreferrer">
          Go to Admin panel
        </a>
      </header>
    </div>
  );
};

export default Homepage;
