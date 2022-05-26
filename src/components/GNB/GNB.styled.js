import styled from 'styled-components';

export const Nav = styled.nav`
  display: flex;
  justify-content: space-between;
  padding: 2.4rem 8rem 0;
`;

export const Logo = styled.h1`
  cursor: pointer;
  font-weight: ${({ theme: { fontWeights } }) => fontWeights.black};
  font-size: ${({ theme: { fontSizes } }) => fontSizes.xxl};
  line-height: ${({ theme: { lineHeights } }) => lineHeights.logo};
  letter-spacing: ${({ theme: { letterSpacings } }) => letterSpacings.tighter};
`;

export const GNBList = styled.ul`
  display: flex;
  gap: 2.4rem;
  font-weight: ${({ theme: { fontWeights } }) => fontWeights.normal};
  line-height: ${({ theme: { lineHeights } }) => lineHeights.base};
`;

export const GNBItem = styled.li`
  cursor: pointer;

  &:hover {
    font-weight: ${({ theme: { fontWeights } }) => fontWeights.bold};
    line-height: ${({ theme: { lineHeights } }) => lineHeights.base};
    text-decoration: underline;
  }
`;

export const Menu = styled.menu``;

export const MenuItem = styled.li`
  background-color: ${({ theme: { colors } }) => colors.white};
  border: 1px solid ${({ theme: { colors } }) => colors.grey4};
  border-radius: 30px;
`;
