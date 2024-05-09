import SwiftUI
import shared


struct ContentView: View {
    @State var viewModel = ValueViewModel()
    @State var stateUi = ViewState(buttonIsLoading:false)

	let greet = Greeting().greet()

	var body: some View {
        VStack{
            Button(
                  action: {
                    if !stateUi.buttonIsLoading {
                      viewModel.onButtonClicked()
                    }
                  }
                ) {
                    Text(stateUi.buttonIsLoading ? "Loading..." : "Click Me")
                }
        }.task {
            for await value in viewModel.state{
                stateUi = value
            }
        }
      
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
