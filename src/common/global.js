import { createGlobalStyle } from 'styled-components';
import reset from 'styled-reset';

const GlobalStyle = createGlobalStyle`
  ${reset}
  html {
    box-sizing: border-box;
    font-size: 10px;
    font-family: 'Noto Sans KR', sans-serif;
    color: #010101;
  }
`;

export default GlobalStyle;
