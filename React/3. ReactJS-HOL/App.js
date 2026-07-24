import { CalculateScore } from "./Components/CalculateScore";

function App() {

  return (
    <div>

      <CalculateScore
        Name={"Mohan Lakshmi Reddy"}
        School={"AP Residential School"}
        total={587}
        goal={6}
      />

    </div>
  );
}

export default App;