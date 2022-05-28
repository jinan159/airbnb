import styled from 'styled-components';
import { BtnBackgroundBorderRemove, FlexCenterSort } from 'common/util.styled';

export const Backdrop = styled.div`
  position: fixed;
  width: 100%;
  height: 100%;
`;

export const Modal = styled.div`
  z-index: 1;
  padding: 6.5rem 8.8rem;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -70%);
  width: 91.6rem;
  height: 51.2rem;
  border-radius: 4rem;
  background-color: ${({ theme: { colors } }) => colors.white};
  box-shadow: 0px 4px 10px rgba(51, 51, 51, 0.1),
    0px 0px 4px rgba(51, 51, 51, 0.05);
`;

export const Carousel = styled.div`
  overflow: hidden;
`;

export const CarouselItemContainer = styled.div`
  ${FlexCenterSort}
  gap: 6.8rem;
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
