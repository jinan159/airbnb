import styled, { css } from 'styled-components';
import { FlexCenterSort } from 'common/util.styled';
import { DateCellProps } from 'components/DateCell/DateCell.types';

export const DateCellContent = styled.li<DateCellProps>`
  ${FlexCenterSort}
  margin-top: 0.4rem;
  width: 4.8rem;
  height: 4.8rem;
  font-weight: ${({ theme: { fontWeights } }) => fontWeights.bold};
  font-size: ${({ theme: { fontSizes } }) => fontSizes.xs};
  line-height: ${({ theme: { lineHeights } }) => lineHeights.shorter};
  color: ${({ past, theme: { colors } }) =>
    past ? colors.grey1 : colors.grey4};

  ${({ flag, past }) =>
    flag &&
    past &&
    css`
      border-radius: 3rem;
      background-color: ${({ theme: { colors } }) => colors.grey1};
      color: ${({ theme: { colors } }) => colors.white};
    `};

  &:hover {
    ${({ past, date }) =>
      date &&
      past &&
      css`
        background-color: ${({ theme: { colors } }) => colors.grey1};
        border-radius: 3rem;
        color: ${({ theme: { colors } }) => colors.white};
        cursor: pointer;
      `}
  }
`;
