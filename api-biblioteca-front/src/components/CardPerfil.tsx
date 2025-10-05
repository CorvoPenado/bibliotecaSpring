// src/components/Card.tsx
import React from 'react';


interface CardProps {
  nome: string;
  email: string;
  idade: number;
}

function CardPerfil({nome, email,idade }: CardProps) {
  
  const cardStyle = {
    border: '1px solid #ddd',
    borderRadius: '8px',
    padding: '16px',
    margin: '10px 0',
    boxShadow: '0 2px 4px rgba(0,0,0,0.1)'
  };
  
  return (
    <div style={cardStyle}>
      <h2><strong>Nome:</strong> <i>{nome}</i> | <strong>Genero:</strong> <i>{email}</i> </h2>
      <hr />
      <h2><strong>Idade:</strong> <i>{idade}</i></h2>
    </div>
  );
}

export default CardPerfil;