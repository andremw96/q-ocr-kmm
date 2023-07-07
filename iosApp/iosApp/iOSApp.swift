import SwiftUI
import shared

@main
struct iOSApp: App {
    init() {
        InjectionHelperKt.doInitKoin()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
