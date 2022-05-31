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
