import React from "react";

function ListofIndianPlayers({ IndianPlayers }) {
  return (
    <ul>
      {IndianPlayers.map((player, index) => (
        <li key={index}>
          Mr. {player}
        </li>
      ))}
    </ul>
  );
}

export default ListofIndianPlayers;