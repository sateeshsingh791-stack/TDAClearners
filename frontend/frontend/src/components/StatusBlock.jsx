export function Loading({ label = 'Loading…' }) {
  return <p className="font-mono text-sm text-slate py-10">{label}</p>;
}

export function ErrorBlock({ message }) {
  return (
    <div className="border border-clay/40 bg-clay/5 text-clay rounded-lg px-4 py-3 text-sm font-medium">
      {message || 'Something went wrong. Please try again.'}
    </div>
  );
}

export function Empty({ title, hint }) {
  return (
    <div className="border border-dashed border-ink/15 rounded-xl px-6 py-12 text-center">
      <p className="font-display text-xl mb-1">{title}</p>
      {hint && <p className="text-sm text-slate">{hint}</p>}
    </div>
  );
}
