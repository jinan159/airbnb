import styled, { css } from 'styled-components';
import { BtnBackgroundBorderRemove } from 'common/util.styled';
import { CheckClearBtnInterface } from './Check.types';

export const CheckContainer = styled.div`
  position: relative;
  display: flex;
  gap: 2.4rem;
  cursor: pointer;
`;

export const CheckClearBtn = styled.button<CheckClearBtnInterface>`
  display: none;

  ${({ checkIn }) =>
    checkIn &&
    css`
      display: block;
      position: absolute;
      top: 50%;
      right: 0;
      transform: translateY(-50%);
      ${BtnBackgroundBorderRemove}
      cursor: pointer;
    `}
`;
