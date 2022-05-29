import styled from 'styled-components';
import { BtnBackgroundBorderRemove } from 'common/util.styled';
import { CarouselItemContainerProps } from 'components/CalendarModal/CalendarModal.types';

export const Backdrop = styled.div`
  position: fixed;
  width: 100%;
  height: 100%;
`;

export const Modal = styled.div`
  z-index: 1;
  padding: 6.5rem 8.8rem;
  position: fixed;
  top: 21rem;
  left: 50%;
  transform: translateX(-50%);
  width: 91.6rem;
  height: 51.2rem;
  border-radius: 4rem;
  background-color: ${({ theme: { colors } }) => colors.white};
  box-shadow: 0px 4px 10px rgba(51, 51, 51, 0.1),
    0px 0px 4px rgba(51, 51, 51, 0.05);
  box-sizing: border-box;
`;

export const Carousel = styled.div`
  overflow: hidden;
`;

export const CarouselItemContainer = styled.div<CarouselItemContainerProps>`
  display: flex;
  justify-content: flex-start;
  flex-wrap: nowrap;
  width: 10000px;
  gap: 6.8rem;
  transform: translateX(${({ pos }) => `${pos}rem`});
  transition: all 0.3s;
  /* transform: translateX(-40.4rem); */
`;

export const Button = styled.button`
  ${BtnBackgroundBorderRemove}
  position: absolute;
  top: 7rem;

  &.left {
    left: 9.7rem;
  }

  &.right {
    right: 9.7rem;
  }
`;
