import styled from 'styled-components';

interface ContainerProps {
  readonly src: string;
}

export const Container = styled.header<ContainerProps>`
  width: 1440px;
  height: 640px;
  margin: 0 auto;
  background-position: center;
  background-repeat: no-repeat;
  background-image: url(${({ src }) => src});
  font-size: ${({ theme: { fontSizes } }) => fontSizes.md};
`;
