//
//  ContentView.swift
//  AI on device uppgifter
//
//  Created by Amel Curic on 2023-10-01.
//

import SwiftUI

struct ContentView: View {
    @State var result = ""
    
    let imageRecognizer = ImageRecognizerModel()
    
    var body: some View {
        button
        resultText
    }
    
    @ViewBuilder
    private var resultText: some View {
        let text = result.isEmpty ? "Select Image" : result
        Text(text)
            .font(.title)
            .padding(.top, 100)
    }
    
    @ViewBuilder
    private var button: some View {
        HStack {
            Spacer()
            
            Button(action: {
                if let image = UIImage(named: "pickachu") {
                    result = imageRecognizer.doImage(image)
                }
            }, label: {
                Image(uiImage: .pickachu)
                    .resizable()
                    .frame(width: 120, height: 80)
            })
            
            Spacer()
            
            Button(action: {
                if let image = UIImage(named: "snorlax") {
                    result = imageRecognizer.doImage(image)
                }
            }, label: {
                Image(uiImage: .snorlax)
                    .resizable()
                    .frame(width: 80, height: 100)
            })
            
            Spacer()
        }
    }
}

#Preview {
    ContentView()
}
