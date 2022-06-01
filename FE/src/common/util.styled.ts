import styled, { css } from 'styled-components';

export const BtnBackgroundBorderRemove = css`
  cursor: pointer;
  background-color: transparent;
  border: none;
`;

export const FlexCenterSort = css`
  display: flex;
  justify-content: center;
  align-items: center;
`;

export const Stick = styled.span`
  margin-right: 2.4rem;
  width: 0.1rem;
  height: 4.4rem;
  background-color: ${({ theme: { colors } }) => colors.grey5};
`;

export const FooterTitle = css`
  font-weight: ${({ theme }) => theme.fontWeights.normal};
  font-size: ${({ theme }) => theme.fontSizes.xxl};
  line-height: ${({ theme }) => theme.lineHeights.logo};
  color: ${({ theme }) => theme.colors.grey1};
`;

export const FooterText = css`
  font-weight: ${({ theme }) => theme.fontWeights.normal};
  font-size: ${({ theme }) => theme.fontSizes.lg};
  line-height: ${({ theme }) => theme.lineHeights.tall};
  color: ${({ theme }) => theme.colors.grey1};
`;
