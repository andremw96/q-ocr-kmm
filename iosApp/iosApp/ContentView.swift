import SwiftUI
import shared


struct ContentView: View {
    var body: some View {
        ComposeView().ignoresSafeArea(.keyboard) // Compose has its own keyboard handler
    }
}

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        return MainScreenIosKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
        
    }
}

