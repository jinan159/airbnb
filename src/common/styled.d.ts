import 'styled-components';

declare module 'styled-components' {
  export interface DefaultTheme {
    fontSizes: {
      xs: string;
      sm: string;
      md: string;
      lg: string;
      xl: string;
      xxl: string;
    };

    fontWeights: {
      normal: string;
      bold: stirng;
      black: string;
    };

    lineHeights: {
      shorter: string;
      short: string;
      base: string;
      tall: string;
      taller: string;
      logo: string;
    };

    letterSpacings: {
      tighter: string;
    };

    colors: {
      secondary: string;
      primary: string;
      black: string;
      grey1: string;
      grey2: string;
      grey3: string;
      grey4: string;
      grey5: string;
      grey6: string;
      white: string;
    };

    borderSizes: {
      small: string;
      medium: string;
    };

    margins: {
      medium: string;
      large: string;
      xLarge: string;
    };

    paddings: {
      medium: string;
      large: string;
    };
  }
}
