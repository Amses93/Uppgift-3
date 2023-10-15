//
//  ContentView.swift
//  AI on device uppgifter
//
//  Created by Amel Curic on 2023-10-01.
//

import SwiftUI

struct ContentView: View {
    @State var fruits: [String] = ["Banan", "Apelsin", "Citron"]
    
    var body: some View {
        NavigationView {
            list
        }
    }
    
    @ViewBuilder
    private var list: some View {
        List(fruits, id: \.self) { item in
            NavigationLink(item, destination: NextView(fruit: item))
        }
    }
}

#Preview {
    ContentView()
}
