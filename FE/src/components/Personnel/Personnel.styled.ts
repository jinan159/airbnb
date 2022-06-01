import styled, { css } from 'styled-components';

import { ClearBtn } from 'common/util.styled';

import { IPersonnelInputClearBtn } from './Presonnel.types';

export const PersonnelContainer = styled.div`
  position: relative;
  cursor: pointer;
`;

export const PersonnelInputClearBtn = styled.button<IPersonnelInputClearBtn>`
  display: none;

  ${({ isClearBtnShow }) =>
    isClearBtnShow &&
    css`
      ${ClearBtn}
    `}
`;
