import styled from 'styled-components';
import { BtnBackgroundBorderRemove } from 'common/util.styled';

export const Backdrop = styled.div`
  position: fixed;
  width: 100%;
  height: 100%;
`;

export const Modal = styled.div`
  z-index: 1;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -85%);
  width: 916px;
  height: 512px;
  border-radius: 4rem;
  background-color: ${({ theme: { colors } }) => colors.white};
  box-shadow: 0px 4px 10px rgba(51, 51, 51, 0.1),
    0px 0px 4px rgba(51, 51, 51, 0.05);
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
