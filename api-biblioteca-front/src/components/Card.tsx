
interface CardProps {
  id:number;
  titulo: string;
  sinopse: string;
  nomeAutor: string;
  nomeGenero: string;
}

function Card({ id,titulo, sinopse,nomeAutor,nomeGenero }: CardProps) {
  
  const cardStyle = {
    border: '1px solid #ddd',
    borderRadius: '8px',
    padding: '16px',
    margin: '10px 0',
    boxShadow: '0 2px 4px rgba(0,0,0,0.1)'
  };
  
  return (
    <div style={cardStyle}>
      <h2><strong>ID:</strong> <i>{id}</i> | <strong>Título:</strong> <i>{titulo}</i> | <strong>Genero:</strong> <i>{nomeGenero}</i> </h2>
      <hr />
      <p><strong>Sinopse:</strong> <i>{sinopse}</i></p>
      <h2><strong>Autor:</strong> <i>{nomeAutor}</i></h2>
    </div>
  );
}

export default Card;