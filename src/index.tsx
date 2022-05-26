import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter } from 'react-router-dom';

import { StyledEngineProvider } from '@mui/styled-engine';
import { ThemeProvider } from 'styled-components';

import theme from 'common/theme';
import GlobalStyle from 'common/global';

import App from 'App';

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement,
);

root.render(
  <React.StrictMode>
    <StyledEngineProvider injectFirst>
      <GlobalStyle />
      <ThemeProvider theme={theme}>
        <BrowserRouter>
          <App />
        </BrowserRouter>
      </ThemeProvider>
    </StyledEngineProvider>
  </React.StrictMode>,
);
