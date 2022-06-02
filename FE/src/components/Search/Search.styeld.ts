import styled, { css } from 'styled-components';

import { FlexCenterSort, BtnBackgroundBorderRemove } from 'common/util.styled';

import { IInputTextStyle } from 'contexts/searchcontext/searchContext.types';

export const SearchBar = styled.main<IInputTextStyle>`
  ${({ isSearchShowing }) =>
    isSearchShowing
      ? css`
          top: 2.3rem;
          padding: 0.8rem 1.6rem;
        `
      : css`
          margin-top: 4rem;
          padding: 1.6rem 1.6rem 1.6rem 4rem;
        `}

  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  background-color: ${({ theme }) => theme.colors.white};
  border: 1px solid ${({ theme }) => theme.colors.grey4};
  border-radius: 6rem;
`;

export const SearchBtn = styled.button`
  ${FlexCenterSort}
  ${BtnBackgroundBorderRemove}
  background-color: ${({ theme }) => theme.colors.primary};
  padding: 0;
  border-radius: 3rem;
  width: 4rem;
  height: 4rem;
`;

export const SearchForm = styled.form<IInputTextStyle>`
  display: flex;
  ${({ isSearchShowing }) =>
    isSearchShowing
      ? css`
          gap: 1.6rem;
        `
      : css`
          gap: 2.4rem;
        `}
`;
