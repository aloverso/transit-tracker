import React, { Component } from 'react';
import TransitModeList from "./components/TransitModeList";
import AddMode from "./components/AddMode";

class App extends Component {

  render() {
    return (
      <div>
        <AddMode />
        <TransitModeList />
      </div>
    );
  }
}


export default App;
