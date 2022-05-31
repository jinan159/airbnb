import styled from 'styled-components';
import { BtnBackgroundBorderRemove } from 'common/util.styled';

export const SearchBar = styled.main`
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  margin-top: 4rem;
  padding: 1.6rem 1.6rem 1.6rem 4rem;
  background-color: ${({ theme }) => theme.colors.white};
  border: 1px solid ${({ theme }) => theme.colors.grey4};
  border-radius: 6rem;
`;

export const SearchBtn = styled.button`
  ${BtnBackgroundBorderRemove}
  background-color: ${({ theme }) => theme.colors.primary};
  border-radius: 3rem;
  padding: 0.8rem;
`;

export const SearchForm = styled.form`
  display: flex;
  gap: 2.4rem;
`;
