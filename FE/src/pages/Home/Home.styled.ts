import styled from 'styled-components';
import { ContainerProps } from 'pages/Home/Home.types';

export const Container = styled.div<ContainerProps>`
  width: 1440px;
  height: 640px;
  background-position: center;
  background-repeat: no-repeat;
  background-image: url(${({ src }) => src});
  font-size: ${({ theme: { fontSizes } }) => fontSizes.md};
`;
