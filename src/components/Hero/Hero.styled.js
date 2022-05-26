import styled from 'styled-components';
import { WrapperMarginCenter } from 'common/utils';

export const Header = styled.header`
  ${WrapperMarginCenter}
  position: relative;
  background-position: center;
  background-repeat: no-repeat;
  background-image: url('/assets/images/hero-img.png');
`;

export const Logo = styled.h1`
  position: absolute;
  top: 2.4rem;
  left: 8rem;
  font-weight: ${({ theme: { fontWeight } }) => fontWeight.black};
  font-size: ${({ theme: { fontSize } }) => fontSize.xxl};
  line-height: ${({ theme: { lineHeight } }) => lineHeight.logo};
  letter-spacing: ${({ theme: { letterSpacing } }) => letterSpacing.tighter};
`;
