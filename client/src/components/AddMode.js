import React, { Component } from 'react'
import { Mutation } from 'react-apollo'
import gql from 'graphql-tag'

const ADD_MODE = gql`
     mutation addModeFunc($name: String!) {
         addMode(name: $name) {
             name
             counter
         }
     }`


const GET_MODES = gql`
    query {
        modes {
            name
            counter
        }
    }
`


class AddMode extends Component {
  state = {
    name: '',
  }



  render() {
    return (
      <div>
        <div>
          <input
            value={this.state.name}
            onChange={e => this.setState({ name: e.target.value })}
            type="text"
            placeholder="Mode of Transit"
          />
        </div>
        <Mutation
          mutation={ADD_MODE}
          variables={{ name: this.state.name }}
          update={(store, { data: { addMode } } ) => {
            console.log(addMode)
            const currentStore = store.readQuery({ query: GET_MODES })
            currentStore.modes.unshift(addMode)
            store.writeQuery({
              query: GET_MODES,
              currentStore
            })
          }}
        >

          {(addModeFunc) => (
            <button onClick={addModeFunc}>
              Submit
            </button>
          )}
        </Mutation>
      </div>
    )
  }
}

export default AddMode