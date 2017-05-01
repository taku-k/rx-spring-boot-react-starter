import React from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import { AppBar } from 'material-ui';
import IconButton from 'material-ui/IconButton';
import BuildIcon from 'material-ui/svg-icons/action/build';

import routes from './routes';

const App = (props) => (
  <MuiThemeProvider>
    <div>
      <AppBar
        title="Reactor Spring Boot React Starter App"
        iconElementLeft={<IconButton><BuildIcon /></IconButton>}
        className="nav-bar"
      />
      { routes }
    </div>
  </MuiThemeProvider>
);

export default App;
