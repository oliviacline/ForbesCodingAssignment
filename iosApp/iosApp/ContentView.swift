import SwiftUI
import shared
import SDWebImageSwiftUI

struct ContentView: View {

    let columns = [
        GridItem(.flexible()),
        GridItem(.flexible())
    ]
    
    @State var dogImages: [String] = []
    
	var body: some View {
        ScrollView {
            LazyVGrid(columns: columns, spacing: 25) {
                ForEach(dogImages, id: \.self) { url in
                    WebImage(url: URL(string: url))
                        .resizable()
                        .placeholder {
                            RoundedRectangle(cornerRadius: 10).foregroundColor(.gray)
                        }
                        .indicator(.activity)
                        .transition(.fade(duration: 0.5))
                        .scaledToFit()
                        .frame(width: 150, height: 150)
                        .clipShape(RoundedRectangle(cornerRadius: 10))
                        .overlay(RoundedRectangle(cornerRadius: 10).stroke(Color.black, lineWidth: 4))
                        .shadow(radius: 10)
                        
                }
            }
            .padding()
        }
        .onAppear{
            dogImages = (OperatorSwitchboard().fetchData(request: DogCall.GetNumerousDogs(dogBreed: DogBreed.boxer, count: 20)) as! DogResponse).dogImageUrls
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
