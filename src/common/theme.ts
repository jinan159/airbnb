import { DefaultTheme } from 'styled-components';

const fontSizes = {
  xs: '1.2rem',
  sm: '1.4rem',
  md: '1.6rem',
  lg: '1.8rem',
  xl: '2rem',
  xxl: '3.2rem',
};

const fontWeights = {
  normal: '400',
  bold: '700',
  black: '900',
};

const lineHeights = {
  shorter: '1.7rem',
  short: '2rem',
  base: '2.3rem',
  tall: '2.6rem',
  taller: '2.9rem',
  logo: '4.6rem',
};

const letterSpacings = {
  tighter: '-0.04rem',
};

const colors = {
  secondary: '#118917',
  primary: '#E84C60',
  black: '#010101',
  grey1: '#333333',
  grey2: '#4F4F4F',
  grey3: '#828282',
  grey4: '#BDBDBD',
  grey5: '#E0E0E0',
  grey6: '#F5F5F7',
  white: '#FFFFFF',
};

const borderSizes = {
  small: '0.19rem',
  medium: '0.3rem',
};

const margins = {
  medium: '1.6rem',
  large: '2.4rem',
  xLarge: '6rem',
};

const paddings = {
  medium: '1.6rem',
  large: '2.4rem',
};

const theme: DefaultTheme = {
  fontSizes,
  fontWeights,
  lineHeights,
  letterSpacings,
  colors,
  borderSizes,
  margins,
  paddings,
};

export default theme;
