import styled, { css } from 'styled-components';
import { FlexCenterSort } from 'common/util.styled';
import {
  DateCellContentProps,
  DateCellCircleProps,
} from 'components/DateCell/DateCell.types';

export const DateCellContent = styled.li<DateCellContentProps>`
  ${FlexCenterSort}
  margin-top: 0.4rem;
  width: 4.8rem;
  height: 4.8rem;
  font-weight: ${({ theme: { fontWeights } }) => fontWeights.bold};
  font-size: ${({ theme: { fontSizes } }) => fontSizes.xs};
  line-height: ${({ theme: { lineHeights } }) => lineHeights.shorter};
  color: ${({ past, theme: { colors } }) =>
    past ? colors.grey1 : colors.grey4};

  ${({ rangeFlag }) =>
    rangeFlag &&
    css`
      background-color: ${({ theme: { colors } }) => colors.grey6};
    `};

  ${({ selectFlag }) =>
    selectFlag &&
    css`
      border-radius: 3rem;
      background-color: ${({ theme: { colors } }) => colors.grey1};
      color: ${({ theme: { colors } }) => colors.white};
    `};

  &:hover {
    ${({ rangeFlag, past, dateInfo }) =>
      !rangeFlag &&
      dateInfo &&
      past &&
      css`
        background-color: ${({ theme: { colors } }) => colors.grey1};
        border-radius: 3rem;
        color: ${({ theme: { colors } }) => colors.white};
        cursor: pointer;
      `}
  }
`;

export const DateCellCircle = styled.div<DateCellCircleProps>`
  ${FlexCenterSort}
  width: 100%;
  height: 100%;

  &:hover {
    ${({ rangeFlag, past, dateInfo }) =>
      rangeFlag &&
      dateInfo &&
      past &&
      css`
        border-radius: 3rem;
        border: 2px solid ${({ theme: { colors } }) => colors.black};
        cursor: pointer;
      `}
  }
`;
