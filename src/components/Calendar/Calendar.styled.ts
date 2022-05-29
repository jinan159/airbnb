import styled, { css } from 'styled-components';
import { FlexCenterSort } from 'common/util.styled';
import { DateCellProps } from 'components/Calendar/Calendar.types';

export const CarouselItem = styled.article`
  margin-top: 0.5rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

export const CalendarTitle = styled.h3`
  margin-bottom: 2.4rem;
  font-weight: ${({ theme: { fontWeights } }) => fontWeights.bold};
  font-size: ${({ theme: { fontSizes } }) => fontSizes.md};
  line-height: ${({ theme: { lineHeights } }) => lineHeights.base};
`;

export const CalendarContent = styled.div``;

export const Week = styled.ul`
  display: flex;
  margin-bottom: 0.4rem;
`;

export const WeekCell = styled.li`
  ${FlexCenterSort}
  width: 4.8rem;
  height: 2.4rem;
  font-weight: ${({ theme: { fontWeights } }) => fontWeights.normal};
  font-size: ${({ theme: { fontSizes } }) => fontSizes.xs};
  line-height: ${({ theme: { lineHeights } }) => lineHeights.shorter};
  color: ${({ theme: { colors } }) => colors.grey3};
`;

export const Dates = styled.ul`
  display: flex;
  flex-wrap: wrap;
  width: 33.6rem;
  height: 33.6rem;
`;

export const DateCell = styled.li<DateCellProps>`
  ${FlexCenterSort}
  margin-top: 0.4rem;
  width: 4.8rem;
  height: 4.8rem;
  font-weight: ${({ theme: { fontWeights } }) => fontWeights.bold};
  font-size: ${({ theme: { fontSizes } }) => fontSizes.xs};
  line-height: ${({ theme: { lineHeights } }) => lineHeights.shorter};
  color: ${({ theme: { colors } }) => colors.grey4};

  &:hover {
    ${({ date }) =>
      date &&
      css`
        background-color: ${({ theme: { colors } }) => colors.grey1};
        border-radius: 3rem;
        color: ${({ theme: { colors } }) => colors.white};
        cursor: pointer;
      `}
  }
`;
