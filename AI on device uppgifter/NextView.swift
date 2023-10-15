//
//  NextView.swift
//  AI on device uppgifter
//
//  Created by Amel Curic on 2023-10-15.
//

import SwiftUI

struct NextView: View {
    @State var fruit: String = ""
    
    var body: some View {
        Text(fruit)
    }
}

#Preview {
    NextView()
}
