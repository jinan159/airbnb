import styled from 'styled-components';

export const Backdrop = styled.div`
  position: fixed;
  width: 100%;
  height: 100%;
  background-color: ${({ theme: { colors } }) => colors.black};
  opacity: 0.5;
`;
