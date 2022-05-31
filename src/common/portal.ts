import React, { useEffect } from 'react';
import ReactDom from 'react-dom';

interface ModalPortalProps {
  children: JSX.Element;
}

function ModalPortal({ children }: ModalPortalProps): JSX.Element {
  const modal = document.getElementById('modal');
  const el: HTMLElement = document.createElement('div');

  useEffect(() => {
    modal?.appendChild(el);
    return () => {
      modal?.removeChild(el);
    };
  }, []);

  return ReactDom.createPortal(children, el);
}

export default ModalPortal;
