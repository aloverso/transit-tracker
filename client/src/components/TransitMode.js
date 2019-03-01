import React, {Component} from 'react'
import gql from "graphql-tag";
import {Mutation} from "react-apollo";

const INCREMENT_MODE = gql`
    mutation incrementModeFunc($id: Int!) {
        incrementMode(id: $id) {
            name
            counter
            id
        }
    }`

const GET_MODES = gql`
    query {
        modes {
            name
            counter
            id
        }
    }
`

class TransitMode extends Component {
  render() {
    return (
      <div>
        <div>
          <span>{this.props.mode.name} ({this.props.mode.counter})</span>
          <span>
            <Mutation
              mutation={INCREMENT_MODE}
              variables={{id: this.props.mode.id}}
              update={(store, {data: {incMode}}) => {
                const currentStore = store.readQuery({query: GET_MODES})
                currentStore.modes.find(mode => mode.id === incMode.id).counter = incMode.counter
                store.writeQuery({
                  query: GET_MODES,
                  currentStore
                })
              }}
            >

            {(incrementModeFunc) => (
              <button onClick={incrementModeFunc}>
                +
              </button>
            )}
          </Mutation>
      </span>
        </div>
      </div>
    )
  }
}

export default TransitMode