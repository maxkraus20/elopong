function Navbar() {
    return (
        <nav className="border-b border-slate-200 bg-white">
            <div className="flex h-16 items-center justify-between px-6">
                <a href="/dashboard"
                    className="
                        rounded-lg
                        px-4 py-2
                        text-base font-medium
                        text-slate-950
                        hover:bg-slate-200
                    "> Dashboard </a>

                <div className="flex items-center gap-4">
                    <a href="/login"
                        className="
                            rounded-lg
                            px-4 py-2
                            text-base font-medium
                            text-slate-950
                            hover:bg-slate-200
                        "> Login </a>
                    <a href="/register"
                        className="
                            rounded-lg
                            px-4 py-2
                            text-base font-medium
                            text-slate-950
                            hover:bg-slate-200
                        "> Register </a>
                </div>
            </div>
        </nav>
    )
}

export default Navbar